package project.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "property")
public class PropertyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "size")
    private Integer size;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", columnDefinition = "ENUM('AVAILABLE', 'UNAVAILABLE')")
    public StateEnum state;

    public enum StateEnum {AVAILABLE, UNAVAILABLE}

    @ElementCollection
    @CollectionTable(
            name="operation",
            joinColumns=@JoinColumn(name="property_id")
    )
    private List<OperationModel> operation;

    public PropertyModel() {

    }

    public PropertyModel(String description, Integer rooms, Integer size, StateEnum state, List<OperationModel> operation) {

        this.description = description;
        this.rooms = rooms;
        this.size = size;
        this.state = state;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public List<OperationModel> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationModel> operation) {
        this.operation = operation;
    }
}
