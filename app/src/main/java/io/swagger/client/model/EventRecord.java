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

import java.util.Date;
import java.util.UUID;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class EventRecord {
  
  @SerializedName("LastChangeDateTimeUtc")
  private Date lastChangeDateTimeUtc = null;
  @SerializedName("Id")
  private UUID id = null;
  @SerializedName("Slug")
  private String slug = null;
  @SerializedName("Title")
  private String title = null;
  @SerializedName("SubTitle")
  private String subTitle = null;
  @SerializedName("Abstract")
  private String _abstract = null;
  @SerializedName("ConferenceDayId")
  private UUID conferenceDayId = null;
  @SerializedName("ConferenceTrackId")
  private UUID conferenceTrackId = null;
  @SerializedName("ConferenceRoomId")
  private UUID conferenceRoomId = null;
  @SerializedName("Description")
  private String description = null;
  @SerializedName("Duration")
  private String duration = null;
  @SerializedName("StartTime")
  private String startTime = null;
  @SerializedName("EndTime")
  private String endTime = null;
  @SerializedName("StartDateTimeUtc")
  private Date startDateTimeUtc = null;
  @SerializedName("EndDateTimeUtc")
  private Date endDateTimeUtc = null;
  @SerializedName("PanelHosts")
  private String panelHosts = null;
  @SerializedName("IsDeviatingFromConBook")
  private Boolean isDeviatingFromConBook = null;
  @SerializedName("BannerImageId")
  private UUID bannerImageId = null;
  @SerializedName("PosterImageId")
  private UUID posterImageId = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Date getLastChangeDateTimeUtc() {
    return lastChangeDateTimeUtc;
  }
  public void setLastChangeDateTimeUtc(Date lastChangeDateTimeUtc) {
    this.lastChangeDateTimeUtc = lastChangeDateTimeUtc;
  }

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
   **/
  @ApiModelProperty(value = "")
  public String getSlug() {
    return slug;
  }
  public void setSlug(String slug) {
    this.slug = slug;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getSubTitle() {
    return subTitle;
  }
  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getAbstract() {
    return _abstract;
  }
  public void setAbstract(String _abstract) {
    this._abstract = _abstract;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public UUID getConferenceDayId() {
    return conferenceDayId;
  }
  public void setConferenceDayId(UUID conferenceDayId) {
    this.conferenceDayId = conferenceDayId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public UUID getConferenceTrackId() {
    return conferenceTrackId;
  }
  public void setConferenceTrackId(UUID conferenceTrackId) {
    this.conferenceTrackId = conferenceTrackId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public UUID getConferenceRoomId() {
    return conferenceRoomId;
  }
  public void setConferenceRoomId(UUID conferenceRoomId) {
    this.conferenceRoomId = conferenceRoomId;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getDuration() {
    return duration;
  }
  public void setDuration(String duration) {
    this.duration = duration;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getStartTime() {
    return startTime;
  }
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getEndTime() {
    return endTime;
  }
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getStartDateTimeUtc() {
    return startDateTimeUtc;
  }
  public void setStartDateTimeUtc(Date startDateTimeUtc) {
    this.startDateTimeUtc = startDateTimeUtc;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getEndDateTimeUtc() {
    return endDateTimeUtc;
  }
  public void setEndDateTimeUtc(Date endDateTimeUtc) {
    this.endDateTimeUtc = endDateTimeUtc;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPanelHosts() {
    return panelHosts;
  }
  public void setPanelHosts(String panelHosts) {
    this.panelHosts = panelHosts;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getIsDeviatingFromConBook() {
    return isDeviatingFromConBook;
  }
  public void setIsDeviatingFromConBook(Boolean isDeviatingFromConBook) {
    this.isDeviatingFromConBook = isDeviatingFromConBook;
  }

  /**
   * If set, refers to a banner ([3-4]:1 aspect ratio) that can be used when little  vertical space is available (e.G. event schedule, or a header section).
   **/
  @ApiModelProperty(value = "If set, refers to a banner ([3-4]:1 aspect ratio) that can be used when little  vertical space is available (e.G. event schedule, or a header section).")
  public UUID getBannerImageId() {
    return bannerImageId;
  }
  public void setBannerImageId(UUID bannerImageId) {
    this.bannerImageId = bannerImageId;
  }

  /**
   * If set, refers to an image of any aspect ratio that should be used where enough  vertical space is available (e.G. event detail).
   **/
  @ApiModelProperty(value = "If set, refers to an image of any aspect ratio that should be used where enough  vertical space is available (e.G. event detail).")
  public UUID getPosterImageId() {
    return posterImageId;
  }
  public void setPosterImageId(UUID posterImageId) {
    this.posterImageId = posterImageId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventRecord eventRecord = (EventRecord) o;
    return (this.lastChangeDateTimeUtc == null ? eventRecord.lastChangeDateTimeUtc == null : this.lastChangeDateTimeUtc.equals(eventRecord.lastChangeDateTimeUtc)) &&
        (this.id == null ? eventRecord.id == null : this.id.equals(eventRecord.id)) &&
        (this.slug == null ? eventRecord.slug == null : this.slug.equals(eventRecord.slug)) &&
        (this.title == null ? eventRecord.title == null : this.title.equals(eventRecord.title)) &&
        (this.subTitle == null ? eventRecord.subTitle == null : this.subTitle.equals(eventRecord.subTitle)) &&
        (this._abstract == null ? eventRecord._abstract == null : this._abstract.equals(eventRecord._abstract)) &&
        (this.conferenceDayId == null ? eventRecord.conferenceDayId == null : this.conferenceDayId.equals(eventRecord.conferenceDayId)) &&
        (this.conferenceTrackId == null ? eventRecord.conferenceTrackId == null : this.conferenceTrackId.equals(eventRecord.conferenceTrackId)) &&
        (this.conferenceRoomId == null ? eventRecord.conferenceRoomId == null : this.conferenceRoomId.equals(eventRecord.conferenceRoomId)) &&
        (this.description == null ? eventRecord.description == null : this.description.equals(eventRecord.description)) &&
        (this.duration == null ? eventRecord.duration == null : this.duration.equals(eventRecord.duration)) &&
        (this.startTime == null ? eventRecord.startTime == null : this.startTime.equals(eventRecord.startTime)) &&
        (this.endTime == null ? eventRecord.endTime == null : this.endTime.equals(eventRecord.endTime)) &&
        (this.startDateTimeUtc == null ? eventRecord.startDateTimeUtc == null : this.startDateTimeUtc.equals(eventRecord.startDateTimeUtc)) &&
        (this.endDateTimeUtc == null ? eventRecord.endDateTimeUtc == null : this.endDateTimeUtc.equals(eventRecord.endDateTimeUtc)) &&
        (this.panelHosts == null ? eventRecord.panelHosts == null : this.panelHosts.equals(eventRecord.panelHosts)) &&
        (this.isDeviatingFromConBook == null ? eventRecord.isDeviatingFromConBook == null : this.isDeviatingFromConBook.equals(eventRecord.isDeviatingFromConBook)) &&
        (this.bannerImageId == null ? eventRecord.bannerImageId == null : this.bannerImageId.equals(eventRecord.bannerImageId)) &&
        (this.posterImageId == null ? eventRecord.posterImageId == null : this.posterImageId.equals(eventRecord.posterImageId));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.lastChangeDateTimeUtc == null ? 0: this.lastChangeDateTimeUtc.hashCode());
    result = 31 * result + (this.id == null ? 0: this.id.hashCode());
    result = 31 * result + (this.slug == null ? 0: this.slug.hashCode());
    result = 31 * result + (this.title == null ? 0: this.title.hashCode());
    result = 31 * result + (this.subTitle == null ? 0: this.subTitle.hashCode());
    result = 31 * result + (this._abstract == null ? 0: this._abstract.hashCode());
    result = 31 * result + (this.conferenceDayId == null ? 0: this.conferenceDayId.hashCode());
    result = 31 * result + (this.conferenceTrackId == null ? 0: this.conferenceTrackId.hashCode());
    result = 31 * result + (this.conferenceRoomId == null ? 0: this.conferenceRoomId.hashCode());
    result = 31 * result + (this.description == null ? 0: this.description.hashCode());
    result = 31 * result + (this.duration == null ? 0: this.duration.hashCode());
    result = 31 * result + (this.startTime == null ? 0: this.startTime.hashCode());
    result = 31 * result + (this.endTime == null ? 0: this.endTime.hashCode());
    result = 31 * result + (this.startDateTimeUtc == null ? 0: this.startDateTimeUtc.hashCode());
    result = 31 * result + (this.endDateTimeUtc == null ? 0: this.endDateTimeUtc.hashCode());
    result = 31 * result + (this.panelHosts == null ? 0: this.panelHosts.hashCode());
    result = 31 * result + (this.isDeviatingFromConBook == null ? 0: this.isDeviatingFromConBook.hashCode());
    result = 31 * result + (this.bannerImageId == null ? 0: this.bannerImageId.hashCode());
    result = 31 * result + (this.posterImageId == null ? 0: this.posterImageId.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventRecord {\n");
    
    sb.append("  lastChangeDateTimeUtc: ").append(lastChangeDateTimeUtc).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  slug: ").append(slug).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  subTitle: ").append(subTitle).append("\n");
    sb.append("  _abstract: ").append(_abstract).append("\n");
    sb.append("  conferenceDayId: ").append(conferenceDayId).append("\n");
    sb.append("  conferenceTrackId: ").append(conferenceTrackId).append("\n");
    sb.append("  conferenceRoomId: ").append(conferenceRoomId).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  duration: ").append(duration).append("\n");
    sb.append("  startTime: ").append(startTime).append("\n");
    sb.append("  endTime: ").append(endTime).append("\n");
    sb.append("  startDateTimeUtc: ").append(startDateTimeUtc).append("\n");
    sb.append("  endDateTimeUtc: ").append(endDateTimeUtc).append("\n");
    sb.append("  panelHosts: ").append(panelHosts).append("\n");
    sb.append("  isDeviatingFromConBook: ").append(isDeviatingFromConBook).append("\n");
    sb.append("  bannerImageId: ").append(bannerImageId).append("\n");
    sb.append("  posterImageId: ").append(posterImageId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
