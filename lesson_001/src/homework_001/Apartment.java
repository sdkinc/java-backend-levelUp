package homework_001;

public class Apartment {
  @PrimaryKey
  private Integer id;

  @Varchar(maxLength = 255)
  @NotNull
  private String name;

  @NotNull
  @Varchar(maxLength = 10)
  private String floor;

  @NotNull
  private boolean isAllowed;

  @NotNull
  @Varchar(maxLength = 20)
  private String room;

  @Varchar(maxLength = 255)
  private String comment;

  public Apartment() {
  }
}
