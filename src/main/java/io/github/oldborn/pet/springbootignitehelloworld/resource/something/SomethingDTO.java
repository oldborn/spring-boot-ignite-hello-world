package io.github.oldborn.pet.springbootignitehelloworld.resource.something;


import lombok.*;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SomethingDTO implements Serializable {

    @QuerySqlField(index = true)
    private Integer id;

    @QuerySqlField(index = true)
    private String anIndexedString;

    @QuerySqlField(index = true)
    private Boolean aBoolean;

    @QuerySqlField(index = false)
    private String aNoneIndexedString;

    //private Map<String,Object> otherThings;

}
