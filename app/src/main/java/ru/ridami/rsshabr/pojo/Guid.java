package ru.ridami.rsshabr.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by Forest on 03.03.2018.
 */

@Element(name = "guid" )
public class Guid
{
    @Attribute
    private String isPermaLink;
    @Text
    private String value;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getIsPermaLink ()
    {
        return isPermaLink;
    }

    public void setIsPermaLink (String isPermaLink)
    {
        this.isPermaLink = isPermaLink;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", isPermaLink = "+isPermaLink+"]";
    }
}