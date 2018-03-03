package jaelson.wagner.silva.upcomingmovies.data.model.common;

import com.squareup.moshi.Json;

import java.io.Serializable;

public abstract class BaseResult implements Serializable {

  private final static long serialVersionUID = -7713754530249067222L;
  @Json(name = "status_message")
  public String statusMessage;
  @Json(name = "success")
  public boolean success;
  @Json(name = "status_code")
  public int statusCode;

}