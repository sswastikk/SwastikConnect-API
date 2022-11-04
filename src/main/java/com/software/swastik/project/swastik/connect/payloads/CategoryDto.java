package com.software.swastik.project.swastik.connect.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto
{
    private Long categoryId;

    @NotEmpty
    @Size(max = 50, message = "Category Name cannot be more than 50 Characters !!!")
    private String categoryName;

    @Size(max = 256, message = "Category Description cannot be more than 256 Characters !!!")
    private String categoryDescription;

    private Date createdOn;
    private Date modifiedOn;
}
