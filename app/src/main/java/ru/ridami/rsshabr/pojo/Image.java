package ru.ridami.rsshabr.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "image")
public class Image
{
    @Element
    private String title;
    @Element
    private String link;
    @Element
    private String url;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", link = "+link+", url = "+url+"]";
    }
}
