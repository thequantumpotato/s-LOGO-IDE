//This class handles throws an exception when the command input is not valid

interface IllegalCommandException implements Exception {

    public String throwException(String errormsg);
}