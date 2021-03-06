package com.airbnb.lottie.model.content;

import android.graphics.PointF;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.EllipseContent;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

import org.json.JSONObject;

public class CircleShape implements ContentModel {
  private final String name;
  private final AnimatableValue<PointF> position;
  private final AnimatablePointValue size;

  private CircleShape(String name, AnimatableValue<PointF> position,
      AnimatablePointValue size) {
    this.name = name;
    this.position = position;
    this.size = size;
  }

  @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new EllipseContent(drawable, layer, this);
  }

  static class Factory {
    private Factory() {
    }

    static CircleShape newInstance(JSONObject json, LottieComposition composition) {
      return new CircleShape(
          json.optString("nm"),
          AnimatablePathValue
              .createAnimatablePathOrSplitDimensionPath(json.optJSONObject("p"), composition),
          AnimatablePointValue.Factory.newInstance(json.optJSONObject("s"), composition));
    }
  }

  public String getName() {
    return name;
  }

  public AnimatableValue<PointF> getPosition() {
    return position;
  }

  public AnimatablePointValue getSize() {
    return size;
  }
}
