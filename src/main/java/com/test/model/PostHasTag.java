package com.test.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.jboss.logging.annotations.Pos;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="post_has_tag")
@Setter
@Getter
@Cacheable
public class PostHasTag extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long post_id;
    private Long tag_id;

    @OneToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id", insertable = false,updatable = false)
    private Tag tag;
}
