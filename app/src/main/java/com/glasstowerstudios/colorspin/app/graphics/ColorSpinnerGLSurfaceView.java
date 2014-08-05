package com.glasstowerstudios.colorspin.app.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class ColorSpinnerGLSurfaceView extends GLSurfaceView {

  public ColorSpinnerGLSurfaceView(Context context){
    super(context);

    setEGLContextClientVersion(2);

    // Set the Renderer for drawing on the GLSurfaceView
    setRenderer(new ColorSpinRenderer());

    // Render the view only when there is a change in the drawing data
    setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
  }
}