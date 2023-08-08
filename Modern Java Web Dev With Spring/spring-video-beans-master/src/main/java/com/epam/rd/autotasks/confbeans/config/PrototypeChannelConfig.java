package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import com.epam.rd.autotasks.confbeans.video.impl.VideoStudioBoilWaterImpl;
import com.epam.rd.autotasks.confbeans.video.impl.VideoStudioBuildHouseImpl;
import com.epam.rd.autotasks.confbeans.video.impl.VideoStudioEscapeSolitude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@Configuration
public class PrototypeChannelConfig {
    private static final VideoStudio BOIL_WATER_STUDIO = new VideoStudioBoilWaterImpl();
    private static final VideoStudio BUILD_HOUSE_STUDIO = new VideoStudioBuildHouseImpl();
    private static final VideoStudio ESCAPE_SOLITUDE = new VideoStudioEscapeSolitude();

    @Bean
    @Scope("prototype")
    public Channel channel() {
        Channel channelBean = new Channel();
        channelBean.addVideo(getBeanBoilWaterVideo());
        channelBean.addVideo(getBeanBuildHouseVideo());
        channelBean.addVideo(getBeanEscapeSolitudeVideo());
        return channelBean;
    }

    @Bean("video1")
    public Video getBeanBoilWaterVideo() {
        return BOIL_WATER_STUDIO.produce();
    }

    @Bean
    public Video getBeanBuildHouseVideo() {
        return BUILD_HOUSE_STUDIO.produce();
    }

    @Bean
    public Video getBeanEscapeSolitudeVideo() {
        return ESCAPE_SOLITUDE.produce();
    }

    @Bean()
    public Channel channelShadow() {
        return channel();
    }
}