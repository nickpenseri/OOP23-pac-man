package it.unibo.view.impl;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.view.api.SoundsEffect;

/** class that handles continuous sounds in the game. */
public class PacManSound implements SoundsEffect {
    private Clip clip;
    private final Logger logger = LoggerFactory.getLogger(SoundsEffectImpl.class);
    private boolean checker;

    /**
     * constructor that given string gets url and opens the clip.
     * 
     * @param stringUrl URL string
     */
    public PacManSound(final String stringUrl) {
        try {
            final URL audioUrl = SoundsEffectImpl.class.getResource(stringUrl);
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioUrl);
            this.clip = AudioSystem.getClip();
            clip.open(audioIn);
            this.checker = true;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playSound() {
        clip.start();
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                this.clip.setFramePosition(0);
            }
        });
    }

    /** closes the clip's audio. */
    public void closeAudio() {
        this.clip.close();
        this.checker = false;
    }

    /**
     * check whether the clip has been closed or not.
     * 
     * @return returns the state of the clip.
     */
    public boolean isChecker() {
        return this.checker;
    }
}
