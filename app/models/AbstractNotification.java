package models;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.Formats;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import com.avaje.ebean.annotation.*;

/**
 * Created by michaeldorman on 5/19/15.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "message_type", discriminatorType = DiscriminatorType.STRING)
@Table(name="messages")
public abstract class AbstractNotification extends Model implements Notification{

    @Id
    public String id;

    @NotNull
    public String fromNumber;

    @NotNull
    public String toNumber;

    @NotNull
    public String accountId;

    @Column(name="created_at")
    @Formats.DateTime(pattern="MM/DD/YY HH24:MI:SS.ff3")
    @CreatedTimestamp
    private Timestamp createdAt;

    @Column(name="updated_at")
    @Formats.DateTime(pattern="MM/DD/YY HH24:MI:SS.ff3")
    @UpdatedTimestamp
    private Timestamp updatedAt;

    //note: only supported in mysql 5.0.3 and after
    @javax.persistence.Column(length=1600)
    public String content;

    public String status;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    @Override
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public void setContent(String content) throws Exception{
        this.content = content;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getFromNumber() {
        return fromNumber;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getToNumber() {return this.toNumber; }

    @Override
    public void setToNumber(String toNumber) { this.toNumber = toNumber;}


    public static Finder<String,AbstractNotification> find = new Finder<String,AbstractNotification>(
            String.class, AbstractNotification.class
    );


    @Override
    public String toString() {
        return "Notification{" +
                "fromNumber='" + fromNumber + '\'' +
                ", toNumber='" + toNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
