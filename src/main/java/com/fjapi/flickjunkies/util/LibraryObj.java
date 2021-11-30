package com.fjapi.flickjunkies.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryObj
{
    private Long libraryId;
    private String name;
    private String username;
}
