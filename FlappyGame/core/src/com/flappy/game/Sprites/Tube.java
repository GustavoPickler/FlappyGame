package com.flappy.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import org.graalvm.compiler.hotspot.nodes.profiling.RandomSeedNode;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52; // seta o tamanho da textura do tubo
    private static final int FLUCTUATION = 130; // pode mover para cima aleatoriamente entre 0 e 130
    private static final int TUBE_GAP = 100; // a distancia entre os tubos é 130
    private static final int LOWEST_OPENING = 120; // aqui setamos que o tubo não pode estar além da tela
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random random;

    public Tube (float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float  x){
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }
}
