package reverie.corporation.com.bmi;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseModel {

    private Integer id;
    private Double result;
    private Integer created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }
}
