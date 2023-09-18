package example03;

public class Staff {
    @PrimaryKey
    private long id;

    @Varchar(maxLength = 255)
    @NotNull
    private String name;

    @NotNull
    @Unique
    private String email;

    public Staff() {
    }
}
