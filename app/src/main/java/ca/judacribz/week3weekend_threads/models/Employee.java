package ca.judacribz.week3weekend_threads.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.judacribz.week3weekend_threads.R;

@Entity(tableName = "employee_table")
public class Employee implements Parcelable {
    @Ignore
    private static final String EMP_SHORT_FORM = "%s, %s (%s)";

    @ColumnInfo(name = "first_name")
    String firstName;

    @ColumnInfo(name = "last_name")
    String lastName;

    @ColumnInfo(name = "street_address")
    String streetAddress;

    @ColumnInfo(name = "city")
    String city;

    @ColumnInfo(name = "state")
    String state;

    @ColumnInfo(name = "zip")
    String zip;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tax_id")
    String taxId;

    @ColumnInfo(name = "position")
    String position;

    @ColumnInfo(name = "department")
    String department;

    public Employee(String firstName,
                    String lastName,
                    String streetAddress,
                    String city,
                    String state,
                    String zip,
                    @NonNull String taxId,
                    String position,
                    String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.taxId = taxId;
        this.position = position;
        this.department = department;
    }

    protected Employee(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        streetAddress = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        taxId = in.readString();
        position = in.readString();
        department = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public static List<String> getSpinnerItemsToEmployee(List<Employee> employees) {
        List<String> emps = new ArrayList<>();
        for (Employee employee : employees) {
            emps.add(getEmpShortForm(employee));
        }

        return emps;
    }

    public static String getEmpShortForm(Employee employee) {
        return String.format(
                Locale.US,
                EMP_SHORT_FORM,
                employee.getLastName(),
                employee.getFirstName(),
                employee.getPosition()
        );
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(streetAddress);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(zip);
        parcel.writeString(taxId);
        parcel.writeString(position);
        parcel.writeString(department);
    }
}
