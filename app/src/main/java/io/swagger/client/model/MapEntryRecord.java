/**
 * Eurofurence API for Mobile Apps
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import io.swagger.client.model.LinkFragment;
import java.util.*;
import java.util.UUID;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class MapEntryRecord {
  
  @SerializedName("Id")
  private UUID id = null;
  @SerializedName("X")
  private Integer X = null;
  @SerializedName("Y")
  private Integer Y = null;
  @SerializedName("TapRadius")
  private Integer tapRadius = null;
  @SerializedName("Links")
  private List<LinkFragment> links = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * \"X\" coordinate of the *center* of a *circular area*, expressed in pixels.
   **/
  @ApiModelProperty(value = "\"X\" coordinate of the *center* of a *circular area*, expressed in pixels.")
  public Integer getX() {
    return X;
  }
  public void setX(Integer X) {
    this.X = X;
  }

  /**
   * \"Y\" coordinate of the *center* of a *circular area*, expressed in pixels.
   **/
  @ApiModelProperty(value = "\"Y\" coordinate of the *center* of a *circular area*, expressed in pixels.")
  public Integer getY() {
    return Y;
  }
  public void setY(Integer Y) {
    this.Y = Y;
  }

  /**
   * \"Radius\" of a *circular area* (the center of which described with X and Y), expressed in pixels.
   **/
  @ApiModelProperty(value = "\"Radius\" of a *circular area* (the center of which described with X and Y), expressed in pixels.")
  public Integer getTapRadius() {
    return tapRadius;
  }
  public void setTapRadius(Integer tapRadius) {
    this.tapRadius = tapRadius;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<LinkFragment> getLinks() {
    return links;
  }
  public void setLinks(List<LinkFragment> links) {
    this.links = links;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MapEntryRecord mapEntryRecord = (MapEntryRecord) o;
    return (this.id == null ? mapEntryRecord.id == null : this.id.equals(mapEntryRecord.id)) &&
        (this.X == null ? mapEntryRecord.X == null : this.X.equals(mapEntryRecord.X)) &&
        (this.Y == null ? mapEntryRecord.Y == null : this.Y.equals(mapEntryRecord.Y)) &&
        (this.tapRadius == null ? mapEntryRecord.tapRadius == null : this.tapRadius.equals(mapEntryRecord.tapRadius)) &&
        (this.links == null ? mapEntryRecord.links == null : this.links.equals(mapEntryRecord.links));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.id == null ? 0: this.id.hashCode());
    result = 31 * result + (this.X == null ? 0: this.X.hashCode());
    result = 31 * result + (this.Y == null ? 0: this.Y.hashCode());
    result = 31 * result + (this.tapRadius == null ? 0: this.tapRadius.hashCode());
    result = 31 * result + (this.links == null ? 0: this.links.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class MapEntryRecord {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  X: ").append(X).append("\n");
    sb.append("  Y: ").append(Y).append("\n");
    sb.append("  tapRadius: ").append(tapRadius).append("\n");
    sb.append("  links: ").append(links).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
