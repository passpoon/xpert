package com.crossent.monitoring.portal.mongroup.moniotring.dto;

public class LogResDto {
    public String id;
    public String hostName;
    public String program;
    public String source;
    public String message;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogResDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", program='").append(program).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
