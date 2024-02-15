package it.unibo.view.impl;

import java.awt.event.KeyEvent;
import java.util.List;

import it.unibo.core.api.SoundEvent;
import it.unibo.input.api.Command;
import it.unibo.view.api.SoundsEffect;

/**
 * Swing Implementation of GameView Interface.
 */
public class GamePanel extends GameViewImpl {
    private static final long serialVersionUID = 1L;
    private final transient SoundsEffect soundBonus;
    private final transient SoundsEffect soundDeath;
    private final transient PacManSound soundPacMan;

    /** constructor that initializes the sounds. */
    public GamePanel() {
        this.soundBonus = new SoundsEffectImpl("/sound/bonus.wav");
        this.soundDeath = new SoundsEffectImpl("/sound/death.wav");
        this.soundPacMan = new PacManSound("/sound/pac-man.wav");
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /**
     * KeyPressed interpreter.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            addCommand(Command.SET_DIR_UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            addCommand(Command.SET_DIR_DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            addCommand(Command.SET_DIR_LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            addCommand(Command.SET_DIR_RIGHT);
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playSounds(final List<SoundEvent> soundEvent) {
        soundEvent.forEach(ev -> {
            switch (ev) {
                case DEATH:
                    this.soundDeath.playSound();
                    break;
                case BONUS:
                    this.soundBonus.playSound();
                    break;
                case PACMAN:
                    this.soundPacMan.playSound();
                    break;
                case PACMAN_STOP:
                    this.soundPacMan.stopAudio();
                    break;
                default:
                    break;
            }
        });
    }

}
