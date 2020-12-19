package com.webappsbusters.parcelmanagement.domain;
import com.webappsbusters.parcelmanagement.domain.Parcel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ParcelDimensionsDto {
    int dummywidth;
    int dummylength;
    int dummyheight;
    public int w(Parcel parcel) { return dummywidth = parcel.getWidth(); }
    public int l(Parcel parcel) { return dummylength = parcel.getLength(); }
    public int h(Parcel parcel) { return dummyheight = parcel.getHeight(); }
}
