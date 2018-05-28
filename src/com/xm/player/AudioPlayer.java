package com.xm.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 语音播放
 *
 * @author XuanMing
 * @create 2018/5/18 10:55
 **/
public class AudioPlayer {
    Player player;
    File music;

    //构造方法  参数是一个.mp3音频文件
    public AudioPlayer(File file) {
        this.music = file;
    }

    //播放方法
    public void play() throws FileNotFoundException, JavaLayerException {

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }
}
