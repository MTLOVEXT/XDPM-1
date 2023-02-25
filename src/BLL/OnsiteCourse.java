package BLL;

public class OnsiteCourse {
            private String CourseID,Location,Days,Time;

            public OnsiteCourse() {
            }

            public OnsiteCourse(String CourseID, String Location, String Days, String Time) {
                this.CourseID = CourseID;
                this.Location = Location;
                this.Days = Days;
                this.Time = Time;
            }

            public String getCourseID() {
                return CourseID;
            }

            public void setCourseID(String CourseID) {
                this.CourseID = CourseID;
            }

            public String getLocation() {
                return Location;
            }

            public void setLocation(String Location) {
                this.Location = Location;
            }

            public String getDays() {
                return Days;
            }

            public void setDays(String Days) {
                this.Days = Days;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }
            
}
