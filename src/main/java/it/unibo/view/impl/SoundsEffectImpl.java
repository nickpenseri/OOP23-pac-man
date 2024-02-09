package it.unibo.view.impl;

import it.unibo.view.api.SoundsEffect;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/** vfdevefvevbfe. */
public class SoundsEffectImpl implements SoundsEffect {

    private final Logger logger = LoggerFactory.getLogger(SoundsEffectImpl.class);
    private final URL audioUrl;
    /**
     * constructor that given a string sets URL.
     * @param stringUrl the string to convert in url.
     */
    public SoundsEffectImpl(final String stringUrl) {
        this.audioUrl = SoundsEffectImpl.class.getResource(stringUrl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playSound() {
        try {
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.audioUrl);
            final Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error(e.getMessage());
        }
    }
}
