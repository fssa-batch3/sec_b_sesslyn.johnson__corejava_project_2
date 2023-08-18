package in.fssa.minimal;

import org.junit.platform.suite.api.*;



@Suite
@SelectClasses({
	TestCreateUser.class,
	TestGetAllUser.class
})
public class TestCreateAndUpdateUser {

}
