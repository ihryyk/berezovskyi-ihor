package com.epam.hw_6.model.entity;

import com.epam.hw_6.model.enums.PassStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pass")
public class Pass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pass_id")
  private long id;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "pass_status")
  private PassStatus passStatus;

  @Column(name = "penalty")
  private int penalty;

  @OneToOne
  @JoinColumn(name = "id_order")
  private Order order;
}
