package ru.ridami.rsshabr.pojo;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by Forest on 04.03.2018.
 */
@Root(name = "category")
public class Hab {

    public String getContent() {

        return content.replace(" ", "\u00A0");
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Text(data = true)
    String content;


}
