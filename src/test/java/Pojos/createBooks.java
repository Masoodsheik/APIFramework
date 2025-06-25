package Pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class createBooks {
   private int id;
    private String name;
    private String author;
    private int published_year;
    private String book_summary;
}
