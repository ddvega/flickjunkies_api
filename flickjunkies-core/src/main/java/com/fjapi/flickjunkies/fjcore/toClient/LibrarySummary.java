package com.fjapi.flickjunkies.fjcore.toClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibrarySummary
{
    private Long libraryId;
    private String name;
    private String username;
    private Integer count;
}
