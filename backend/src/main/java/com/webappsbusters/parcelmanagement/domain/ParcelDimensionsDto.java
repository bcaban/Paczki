import com.webappsbusters.parcelmanagement.domain.Parcel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@NoArgsConstructor
@Data
@ToString
public class ParcelDimensionsDto {
    int parcel_width;
    int parcel_length;
    int parcel_height;
    public int w(Parcel parcel) { return parcel_width = parcel.getWidth(); }
    public int l(Parcel parcel) { return parcel_length = parcel.getLength(); }
    public int h(Parcel parcel) { return parcel_height = parcel.getHeight(); }
}