package com.example.hrms.core.entities.employeeDetails;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class imageAdapter {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "my_cloud_name",
            "api_key", "my_api_key",
            "api_secret", "my_api_secret"));

}
