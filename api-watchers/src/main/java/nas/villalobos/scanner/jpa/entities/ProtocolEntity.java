package nas.villalobos.scanner.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProtocolEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String protocol;
	
	protected ProtocolEntity() {}
	
	public ProtocolEntity(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public String toString() {
		return "ProtocolEntity [id=" + id + ", protocol=" + protocol + "]";
	}
}
