package com.cm.manage.model.order;


import java.io.Serializable;

public class MatchForMapping  implements Serializable {

    private Long id;

    private String matchName;

    private String matchShortName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchShortName() {
        return matchShortName;
    }

    public void setMatchShortName(String matchShortName) {
        this.matchShortName = matchShortName;
    }
}
