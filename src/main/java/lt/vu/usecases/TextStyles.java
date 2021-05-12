package lt.vu.usecases;

import lt.vu.decorators.TextStyleFont;
import lt.vu.services.ProvideString;


import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

@Model
public class TextStyles implements Serializable {
    @Inject
    private TextStyleFont textStyle;

    @Inject
    private ProvideString provideString;

    public String getStyle(){
        return textStyle.style();
    }

    public String getText(){
        return provideString.text();
    }
}

