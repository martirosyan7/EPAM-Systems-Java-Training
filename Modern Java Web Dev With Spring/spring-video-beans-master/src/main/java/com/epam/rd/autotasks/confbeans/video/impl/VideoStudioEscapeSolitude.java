package com.epam.rd.autotasks.confbeans.video.impl;

import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;

import java.time.LocalDateTime;

public class VideoStudioEscapeSolitude implements VideoStudio {
    @Override
    public Video produce() {
        String name = "How to escape solitude";
        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 10, 10, 12);
        return new Video(name, localDateTime);
    }
}
