package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.FlappyGame;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;
    public MenuState(GameStateManager gsm) { // O estado do jogo
        super(gsm);
        background = new Texture("background.png");
        playButton = new Texture("playButton.png");
    }

    @Override
    public void handleInput() { // Quando o usuário clica ele abre o método playstate que vai começar o jogo e o dispose que carrega as texturas
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappyGame.WIDTH, FlappyGame.HEIGHT);
        // Aqui setamos as coordenada do botão, que pega a altura total em FlappyGame.Width e FlappyGame.Height dividido por 2, centralizando o botão
        sb.draw(playButton, (FlappyGame.WIDTH/2) - (playButton.getWidth()/2), FlappyGame.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() { //
        background.dispose();
        playButton.dispose();
    }
}
