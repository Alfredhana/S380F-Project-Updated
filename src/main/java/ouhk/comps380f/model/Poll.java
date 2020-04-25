package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "poll")
public class Poll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pollid;
    
//    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL, orphanRemoval = true)
    private String topic;
    private String optionone;
    private String optiontwo;
    private String optionthree;
    private String optionfour;

    public Poll() {
    }

    public Poll(String topic, String optionone, String optiontwo, String optionthree, String optionfour) {
        this.topic= topic;
        this.optionone = optionone;
        this.optiontwo = optiontwo;
        this.optionthree = optionthree;
        this.optionfour = optionfour;
    }

    public long getPollid() {
        return pollid;
    }

    public void setPollid(long pollid) {
        this.pollid = pollid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOptionone() {
        return optionone;
    }

    public void setOptionone(String optionone) {
        this.optionone = optionone;
    }

    public String getOptiontwo() {
        return optiontwo;
    }

    public void setOptiontwo(String optiontwo) {
        this.optiontwo = optiontwo;
    }

    public String getOptionthree() {
        return optionthree;
    }

    public void setOptionthree(String optionthree) {
        this.optionthree = optionthree;
    }

    public String getOptionfour() {
        return optionfour;
    }

    public void setOptionfour(String optionfour) {
        this.optionfour = optionfour;
    }
}
