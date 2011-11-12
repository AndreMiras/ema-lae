/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author pc
 */

@Entity
@Table(name = "internshipSupervisors")
public class InternshipSupervisor extends UserProfile implements Serializable
{

}
