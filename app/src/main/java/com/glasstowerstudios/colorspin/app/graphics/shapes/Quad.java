package com.glasstowerstudios.colorspin.app.graphics.shapes;

import android.opengl.GLES20;

import com.glasstowerstudios.colorspin.app.graphics.ColorSpinRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Quad {

  private FloatBuffer vertexBuffer;
  private ShortBuffer drawListBuffer;

  private final String vertexShaderCode =
      "attribute vec4 vPosition;" +
          "void main() {" +
          "  gl_Position = vPosition;" +
          "}";

  private final String fragmentShaderCode =
      "precision mediump float;" +
          "uniform vec4 vColor;" +
          "void main() {" +
          "  gl_FragColor = vColor;" +
          "}";

  private int mProgram;
  // number of coordinates per vertex in this array
  static final int COORDS_PER_VERTEX = 3;
  static float quadCoords[] = {
      -0.5f,  0.5f, 0.0f,   // top left
      -0.5f, -0.5f, 0.0f,   // bottom left
      0.5f, -0.5f, 0.0f,   // bottom right
      0.5f,  0.5f, 0.0f }; // top right

  private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

  public Quad() {
    // initialize vertex byte buffer for shape coordinates
    ByteBuffer bb = ByteBuffer.allocateDirect(
        // (# of coordinate values * 4 bytes per float)
        quadCoords.length * 4);
    bb.order(ByteOrder.nativeOrder());
    vertexBuffer = bb.asFloatBuffer();
    vertexBuffer.put(quadCoords);
    vertexBuffer.position(0);

    // initialize byte buffer for the draw list
    ByteBuffer dlb = ByteBuffer.allocateDirect(
        // (# of coordinate values * 2 bytes per short)
        drawOrder.length * 2);
    dlb.order(ByteOrder.nativeOrder());
    drawListBuffer = dlb.asShortBuffer();
    drawListBuffer.put(drawOrder);
    drawListBuffer.position(0);

    int vertexShader = ColorSpinRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
    int fragmentShader = ColorSpinRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

    mProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
    GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
    GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
    GLES20.glLinkProgram(mProgram);                  // creates OpenGL ES program executables

  }
}