package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class ChannelWithPhantomVideoStudioConfig{
    private static final String FRANCHISE = "Cat & Curious %d";
    private static int startVideoValue = 1;
    private static LocalDateTime localDateTime = LocalDateTime.of(2001, 10, 18, 10, 0);

    @Bean
    public Channel channel() {
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(nextRelease());
        }
        return channel;
    }

    @Bean
    @Scope("prototype")
    public Video nextRelease() {
        return getVideo();
    }

    private Video getVideo() {
        Video video = new Video(String.format(FRANCHISE, startVideoValue), localDateTime);
        startVideoValue++;
        localDateTime = localDateTime.plusYears(2);
        return video;
    }
}