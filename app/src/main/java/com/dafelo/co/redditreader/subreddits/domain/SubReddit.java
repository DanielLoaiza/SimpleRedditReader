package com.dafelo.co.redditreader.subreddits.domain;


import com.dafelo.co.redditreader.main.domain.RedditObject;


import org.parceler.Parcel;

import java.util.List;

/**
 * Created by root on 7/01/17.
 */
@Parcel
public class SubReddit implements RedditObject {

     String bannerImg;
     String submitTextHtml;
     String id;
     String submitText;
     String displayName;
     String headerImg;
     String descriptionHtml;
     String title;
     String publicDescriptionHtml;
     String iconImg;
     String headerTitle;
     String description;
     Integer subscribers;
     String submitTextLabel;
     String lang;
     String name;
     Integer created;
     String url;
     Integer createdUtc;
     String publicDescription;
     String subredditType;

    public SubReddit() {}

    public SubReddit(String bannerImg, String submitTextHtml,
                     String submitText, String displayName, String headerImg, String descriptionHtml,
                     String title, String publicDescriptionHtml, String iconImg, String headerTitle,
                     String description, Integer subscribers, String submitTextLabel, String lang,
                     String name, Integer created, String url, Integer createdUtc,
                     String publicDescription, String subredditType) {
        this.bannerImg = bannerImg;
        this.submitTextHtml = submitTextHtml;
        this.submitText = submitText;
        this.displayName = displayName;
        this.headerImg = headerImg;
        this.descriptionHtml = descriptionHtml;
        this.title = title;
        this.publicDescriptionHtml = publicDescriptionHtml;
        this.iconImg = iconImg;
        this.headerTitle = headerTitle;
        this.description = description;
        this.subscribers = subscribers;
        this.submitTextLabel = submitTextLabel;
        this.lang = lang;
        this.name = name;
        this.created = created;
        this.url = url;
        this.createdUtc = createdUtc;
        this.publicDescription = publicDescription;
        this.subredditType = subredditType;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getSubmitTextHtml() {
        return submitTextHtml;
    }

    public void setSubmitTextHtml(String submitTextHtml) {
        this.submitTextHtml = submitTextHtml;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPublicDescriptionHtml() {
        return publicDescriptionHtml;
    }

    public void setPublicDescriptionHtml(String publicDescriptionHtml) {
        this.publicDescriptionHtml = publicDescriptionHtml;
    }


    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public String getSubmitTextLabel() {
        return submitTextLabel;
    }

    public void setSubmitTextLabel(String submitTextLabel) {
        this.submitTextLabel = submitTextLabel;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Integer getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Integer createdUtc) {
        this.createdUtc = createdUtc;
    }

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public String getSubredditType() {
        return subredditType;
    }

    public void setSubredditType(String subredditType) {
        this.subredditType = subredditType;
    }

}
