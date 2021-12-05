package com.flappy.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Fish {
    private static final int GRAVITY = -15; // a gravidade quando pula
    private static final int MOVEMENT = 100; // o movimento quando
    private Vector3 position;
    private Vector3 velocity;

    private Texture texture;

    public Fish(int x, int y) {
        position = new Vector3 (x, y, 0);
        velocity = new Vector3 (0, 0, 0);
        texture = new Texture("fish.png");
    }

    public void update(float dt) {
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0); // adiciona gravidade para o eixo y da velocidade
        velocity.scl(dt); // vai multiplicar tudo pelo delta time
        position.add(MOVEMENT * dt, velocity.y, 0); // no position setamos a posição dele pelo eixo y
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt); // no velocity scale revertemos o que escalamos anteriormente para ser adicionado novamente no proximo frame
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void jump() {
        velocity.y = 250; // a gravidade para ele cair é negativa então a gente seta o pulo com velocidade positiva
    }
}
