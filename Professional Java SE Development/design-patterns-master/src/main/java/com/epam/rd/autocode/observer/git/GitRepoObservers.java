package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GitRepoObservers {
    static class WebHookClass implements WebHook {

        String branch;
        Event.Type type;
        List<Event> events = new ArrayList<>();

        WebHookClass(String branch, Event.Type type) {
            this.branch = branch;
            this.type = type;
        }

        @Override
        public String branch() {
            return branch;
        }

        @Override
        public Event.Type type() {
            return type;
        }

        @Override
        public List<Event> caughtEvents() {
            return events;
        }

        @Override
        public void onEvent(Event event) {
            if (event.type() == this.type() && event.branch() == this.branch()) {
                events.add(event);
            }
        }
    }
    static class Rep implements Repository {
        List<Commit> commits = new ArrayList<>();
        List<WebHook> webHooks = new ArrayList<>();
        List<Event> events = new ArrayList<>();


        @Override
        public void addWebHook(WebHook webHook) {
            webHooks.add(webHook);
        }

        @Override
        public Commit commit(String branch, String author, String[] changes) {
            Commit commit = new Commit(author, changes);
            List<Commit> list = new ArrayList<>();
            list.add(commit);
            Event event = new Event(Event.Type.COMMIT, branch, list);
            events.add(event);
            for (WebHook webHook : webHooks) {
                webHook.onEvent(event);
            }
            commits.add(commit);
            return commit;
        }

        @Override
        public void merge(String sourceBranch, String targetBranch) {
            List<Commit> sourceCommits = new ArrayList<>();

            for (Event event: events) {
                if (event.branch() == sourceBranch && event.type() == Event.Type.COMMIT) {
                    sourceCommits.addAll(event.commits());
                }
                if (event.branch() == targetBranch && event.type() == Event.Type.MERGE) {
                    sourceCommits = new ArrayList<>();
                }
            }
            if (sourceCommits.size() == 0) return;
            Event event = new Event(Event.Type.MERGE, targetBranch, sourceCommits);
            events.add(event);
            for (WebHook webHook : webHooks) {
                webHook.onEvent(event);
            }
        }
    }

    public static Repository newRepository(){
        return new Rep();
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHookClass(branchName, Event.Type.MERGE);
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHookClass(branchName, Event.Type.COMMIT);
    }


}
