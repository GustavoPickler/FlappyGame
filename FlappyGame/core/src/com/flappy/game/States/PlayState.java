package com.flappy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flappy.game.FlappyGame;
import com.flappy.game.Sprites.Fish;
import com.flappy.game.Sprites.Tube;

public class PlayState extends State{
    private static final int TUBE_SPACING = 125; // aqui é o espaço entre os tubos
    private static final int TUBE_COUNT = 4;
    private Fish fish;
    private Texture background;
    private Tube tube;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) { // quando o playstate é chamado ele seta a posição do Peixe na tela
        super(gsm);
        fish = new Fish(50,200);
        //setOrtho false porque queremos que o Y comece por baixo da tela,
        cam.setToOrtho(false, FlappyGame.WIDTH / 2, FlappyGame.HEIGHT / 2);
        background = new Texture("background.png"); // e carrega o background
        tube = new Tube(100);

        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            fish.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        fish.update(dt);
        cam.position.x = fish.getPosition().x + 80; // vai setar a camera um pouco na frente do passaro

        for (Tube tube : tubes){
            // se o tubo ta fora da esquerda da tela então executamos essa função
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) { // aqui vai renderizar a tela do jogo, mostrar o peixe nas coordenadas 50 50
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        // aqui renderiza o fundo e divide o eixo x por 2 para o fundo não ficar centralizado e cortar
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0, cam.viewportWidth, cam.viewportHeight);
        sb.draw(fish.getTexture(), fish.getPosition().x, fish.getPosition().y); // aqui pegamos os atributos da classe Bird
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
