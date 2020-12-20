package com.webappsbusters.parcelmanagement.domain;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ParcelDimensions {
    int dummywidth;
    int dummylength;
    int dummyheight;
    public int getWidth(Parcel parcel) { return dummywidth = parcel.getWidth(); }
    public int getLength(Parcel parcel) { return dummylength = parcel.getLength(); }
    public int getHeight(Parcel parcel) { return dummyheight = parcel.getHeight(); }
}
