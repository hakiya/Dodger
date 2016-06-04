package com.pewpew.dodger.MainView.FrameDrawer;

import com.pewpew.dodger.MainView.IMainView;
import com.pewpew.dodger.ObjectPool.IObjectPool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hakiyama on 9/10/15.
 */
public class FrameImpl implements IFrame {


    //todo: Need to figure out how to initialize with mainView!!

    private float xPos;
    private float yPos;
    private float xSize;
    private float ySize;
    private float alpha;
    private float angle;
    private float xAtlas;
    private float yAtlas;
    public FloatBuffer vertBuffer;    //Vertices buffer
    public FloatBuffer textureBuffer;    //Texture buffer
    public float[] textureCoordinates;    //Texture coordinates
    public float[] vertices;    //Vertices for location of image
    private IObjectPool objectPool;
    private IMainView mainView;
    private float xAtlasLen;
    private float yAtlasLen;

    public FrameImpl() {
        vertices = new float[12];
        textureCoordinates = new float[8];
        vertBuffer = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureBuffer = ByteBuffer.allocateDirect(textureCoordinates.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    @Override
    public void draw(GL10 gl) {

        gl.glPushMatrix();

        gl.glTranslatef(mainView.gameSizeToScreen(xPos),
                mainView.gameSizeToScreen(yPos), 1.0f);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, MyGLRenderer.textures[0]);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
//        if(mHasShadow) {
//            gl.glTranslatef(SHADOW_SHIFT, -1.0f * SHADOW_SHIFT, 0.0f);
//            gl.glRotatef(mAngle * 57.295779579f, 0.0f, 0.0f, 1.0f);
//            gl.glColor4f(0.9f, 0.9f, 0.9f, alpha * 0.5f);
//            gl.glFrontFace(GL10.GL_CW);
//            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertBuffer);
//            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
//            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
//            gl.glRotatef(mAngle * -57.295779579f, 0.0f, 0.0f, 1.0f);
//            gl.glTranslatef(-1.0f * SHADOW_SHIFT, SHADOW_SHIFT, 0.0f);
//        }
        gl.glRotatef(angle * 57.295779579f, 0.0f, 0.0f, 1.0f);
        gl.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glPopMatrix();
    }

    @Override
    public IFrame withPos(float x, float y) {
        this.xPos = x;
        this.yPos = y;
        return this;
    }

    @Override
    public IFrame withAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    @Override
    public IFrame withAngle(float angle) {
        this.angle = angle;
        return this;
    }

    @Override
    public IFrame withIncrementAngle(float angleIncrement) {
        this.angle += angleIncrement;
        return this;
    }

    @Override
    public IFrame withAtlas(float xAtlas, float yAtlas) {
        this.xAtlas = xAtlas;
        this.yAtlas = yAtlas;
        return this;
    }

    @Override
    public IFrame withAtlasLen(float xAtlasLen, float yAtlasLen) {
        this.xAtlasLen = xAtlasLen;
        this.yAtlasLen = yAtlasLen;
        return this;
    }

    @Override
    public IFrame withSize(float xSize, float ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        return this;
    }

    @Override
    public IFrame withClean() {
        withPos(0.0f, 0.0f);
        withSize(0.0f, 0.0f);
        withAtlas(0.0f, 0.0f);
        withAtlasLen(0.0f, 0.0f);
        withAlpha(0.0f);
        withAngle(0.0f);
        withView(null);

        for (int index = 0; index < vertices.length - 1 ; index++) {
            vertices[index] = 0.0f;
        }
        for (int index = 0; index < textureCoordinates.length - 1; index++) {
            textureCoordinates[index] = 0.0f;
        }
        return this;
    }

    @Override
    public void setObjectPool(IObjectPool objectPool) {
        this.objectPool = objectPool;
    }

    @Override
    public void free() {
        objectPool.freeObject(this);
    }

    @Override
    public IFrame withView(IMainView mainView) {
        this.mainView = mainView;
        return this;
    }

    @Override
    public IFrame withRefreshVertices() {
        vertices[0] = mainView.gameSizeToScreen(xSize) * -1.0f;
        vertices[1] = mainView.gameSizeToScreen(ySize) * -1.0f;
        vertices[2] = 0.0f;

        vertices[3] = mainView.gameSizeToScreen(xSize) * -1.0f;
        vertices[4] = mainView.gameSizeToScreen(ySize) * 1.0f;
        vertices[5] = 0.0f;

        vertices[6] = mainView.gameSizeToScreen(xSize) * 1.0f;
        vertices[7] = mainView.gameSizeToScreen(ySize) * -1.0f;
        vertices[8] = 0.0f;

        vertices[9] = mainView.gameSizeToScreen(xSize) * 1.0f;
        vertices[10] = mainView.gameSizeToScreen(ySize) * 1.0f;
        vertices[11] = 0.0f;

        vertBuffer.put(vertices);
        vertBuffer.position(0);

        textureCoordinates[0] = ((xAtlas + 0.0f) / 8.0f) + 0.0f / 128.0f;
        textureCoordinates[1] = ((yAtlas + yAtlasLen) / 8.0f) - 0.0f / 128.0f;

        textureCoordinates[2] = ((xAtlas + 0.0f) / 8.0f) + 0.0f / 128.0f;
        textureCoordinates[3] = ((yAtlas + 0.0f) / 8.0f) + 0.0f / 128.0f;

        textureCoordinates[4] = ((xAtlas + xAtlasLen) / 8.0f) + 0.0f / 128.0f;
        textureCoordinates[5] = ((yAtlas + yAtlasLen) / 8.0f) + 0.0f / 128.0f;

        textureCoordinates[6] = ((xAtlas + xAtlasLen) / 8.0f) + 0.0f / 128.0f;
        textureCoordinates[7] = ((yAtlas + 0.0f) / 8.0f) + 0.0f / 128.0f;

        textureBuffer.put(textureCoordinates);
        textureBuffer.position(0);
        return this;
    }

}
