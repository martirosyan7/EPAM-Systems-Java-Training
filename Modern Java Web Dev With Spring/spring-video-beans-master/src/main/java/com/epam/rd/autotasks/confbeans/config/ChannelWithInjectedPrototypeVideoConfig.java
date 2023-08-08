package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Configuration
public class ChannelWithInjectedPrototypeVideoConfig extends Channel {
    private static final String NAME = "Cat Failure Compilation";
    private static LocalDateTime release = LocalDateTime.of(2001, 10, 18, 10, 0);

    @Override
    public Stream<Video> videos() {
        Channel channel = new Channel();
        for (int i = 0; i < 7; i++) {
            channel.addVideo(getVideo());
        }
        return channel.videos();
    }

    private Video getVideo() {
        Video video = new Video(NAME, release);
        release = release.plusDays(1);
        return video;
    }

    @Bean
    @Scope("prototype")
    public Video getBeanVideo() {
        return getVideo();
    }

}
