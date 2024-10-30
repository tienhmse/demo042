package demo02.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class ControlHello {
	@Autowired
    private DataSource dataSource;

	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
    
	public ResponseEntity<String> getContent(@RequestBody String l_body){
        //return new ResponseEntity<>("Hello em Tiến chào Phòng kỹ thuật", HttpStatus.OK);
		String sql = "hello";
		
		try (Connection conn = dataSource.getConnection();
	            
			 CallableStatement stmt = conn.prepareCall("insert into table_test(v,v1,v2) values(?,?,?)") ;
	            ) {
				
				stmt.setString(1, l_body);
				stmt.setString(2, l_body);
				stmt.setString(3, l_body);
	            
				stmt.execute();
	            
				//conn.close() Có thể vào vòng try là không cần close. Còn ngoài () try thì sẽ phải close nếu không nó sẽ chiếm session
				return new ResponseEntity<>("Đã insert dữ liệu", HttpStatus.OK);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>("Loi thuc hien", HttpStatus.NOT_FOUND);
	        } 

    }

}
