package com.epam.rd.autotasks.confbeans.video.impl;

import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;

import java.time.LocalDateTime;

public class VideoStudioBoilWaterImpl implements VideoStudio {
    @Override
    public Video produce() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 10);
        String name = "How to boil water";
        return new Video(name, localDateTime);
    }
}
