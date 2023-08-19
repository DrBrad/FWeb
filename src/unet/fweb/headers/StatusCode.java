package unet.fweb.headers;

public enum StatusCode {

    //1xx: Informational
    CONTINUE(100, new byte[]{ 'C', 'o', 'n', 't', 'i', 'n', 'u', 'e' }),
    SWITCHING_PROTOCOLS(101, new byte[]{ 'S', 'w', 'i', 't', 'c', 'h', 'i', 'n', 'g', 0x20, 'P', 'r', 'o', 't', 'o', 'c', 'o', 'l', 's' }),
    PROCESSING(102, new byte[]{ 'P', 'r', 'o', 'c', 'e', 's', 's', 'i', 'n', 'g' }),
    EARLY_HINTS(103, new byte[]{ 'E', 'a', 'r', 'l', 'y', 0x20, 'H', 'i', 'n', 't', 's' }),

    //2xx: Success
    OK(200, new byte[]{ 'O', 'K' }),
    CREATED(201, new byte[]{ 'C', 'r', 'e', 'a', 't', 'e', 'd' }),
    ACCEPTED(202, new byte[]{ 'A', 'c', 'c', 'e', 'p', 't', 'e', 'd' }),
    NON_AUTHORITATIVE_INFORMATION(203, new byte[]{ 'N', 'o', 'n', '-', 'A', 'u', 't', 'h', 'o', 'r', 'a', 't', 'i', 'v', 'e', 0x20, 'I', 'n', 'f', 'o', 'r', 'm', 'a', 't', 'i', 'o', 'n' }),
    NO_CONTENT(204, new byte[]{ 'N', 'o', 0x20, 'C', 'o', 'n', 't', 'e', 'n', 't' }),
    RESET_CONTENT(205, new byte[]{ 'R', 'e', 's', 'e', 't', 0x20, 'C', 'o', 'n', 't', 'e', 'n', 't' }),
    PARTIAL_CONTENT(206, new byte[]{ 'P', 'a', 'r', 't', 'i', 'a', 'l', 0x20, 'C', 'o', 'n', 't', 'e', 'n', 't' }),
    MULTI_STATUS(207, new byte[]{ 'M', 'u', 'l', 't', 'i', '-', 'S', 't', 'a', 't', 'u', 's' }),
    ALREADY_REPORTED(208, new byte[]{ 'A', 'l', 'r', 'e', 'a', 'd', 'y', 0x20, 'R', 'e', 'p', 'o', 'r', 't', 'e', 'd' }),
    IM_USED(226, new byte[]{ 'I', 'M', 0x20, 'U', 's', 'e', 'd' }),

    //3xx: Redirection
    MULTIPLE_CHOICES(300, new byte[]{ 'M', 'u', 'l', 't', 'i', 'p', 'l', 'e', 0x20, 'C', 'h', 'o', 'i', 'c', 'e' }),
    MOVED_PERMANENTLY(301, new byte[]{ 'M', 'o', 'v', 'e', 'd', 0x20, 'P', 'e', 'r', 'm', 'a', 'n', 'e', 'n', 't', 'l', 'y' }),
    FOUND(302, new byte[]{ 'F', 'o', 'u', 'n', 'd' }),
    SEE_OTHER(303, new byte[]{ 'S', 'e', 'e', 0x20, 'O', 't', 'h', 'e', 'r' }),
    NOT_MODIFIED(304, new byte[]{ 'N', 'o', 't', 0x20, 'M', 'o', 'd', 'i', 'f', 'i', 'e', 'd' }),
    USE_PROXY(305, new byte[]{ 'U', 's', 'e', 0x20, 'P', 'r', 'o', 'x', 'y' }),
    TEMPORARY_REDIRECT(307, new byte[]{ 'T', 'e', 'm', 'p', 'o', 'r', 'a', 'r', 'y', 0x20, 'R', 'e', 'd', 'i', 'r', 'e', 'c', 't' }),
    PERMANENT_REDIRECT(308, new byte[]{ 'M', 'o', 'v', 'e', 'd', 0x20, 'R', 'e', 'd', 'i', 'r', 'e', 'c', 't' }),

    //4xx: Client Error
    BAD_REQUEST(400, new byte[]{ 'B', 'a', 'd', 0x20, 'R', 'e', 'q', 'u', 'e', 's', 't' }),
    UNAUTHORIZED(401, new byte[]{ 'U', 'n', 'a', 'u', 't', 'h', 'o', 'r', 'i', 'z', 'e', 'd' }),
    PAYMENT_REQUIRED(402, new byte[]{ 'P', 'a', 'y', 'm', 'e', 'n', 't', 0x20, 'R', 'e', 'q', 'u', 'i', 'r', 'e', 'd' }),
    FORBIDDEN(403, new byte[]{ 'F', 'o', 'r', 'b', 'i', 'd', 'd', 'e', 'n' }),
    NOT_FOUND(404, new byte[]{ 'N', 'o', 't', 0x20, 'F', 'o', 'u', 'n', 'd' }),
    METHOD_NOT_ALLOWED(405, new byte[]{ 'M', 'e', 't', 'h', 'o', 'd', 0x20, 'N', 'o', 't', 0x20, 'A', 'l', 'l', 'o', 'w', 'e', 'd' }),
    NOT_ACCEPTABLE(406, new byte[]{ 'N', 'o', 't', 0x20, 'A', 'c', 'c', 'e', 'p', 't', 'a', 'b', 'l', 'e' }),
    PROXY_AUTHENTICATION_REQUIRED(407, new byte[]{ 'P', 'r', 'o', 'x', 'y', 0x20, 'A', 'u', 't', 'h', 'e', 'n', 't', 'i', 'c', 'a', 't', 'i', 'o', 'n', 0x20, 'R', 'e', 'q', 'u', 'i', 'r', 'e', 'd' }),
    REQUEST_TIMEOUT(408, new byte[]{ 'R', 'e', 'q', 'u', 'e', 's', 't', 0x20, 'T', 'i', 'm', 'e', 'o', 'u', 't' }),
    CONFLICT(409, new byte[]{ 'C', 'o', 'n', 'f', 'l', 'i', 'c', 't' }),
    GONE(410, new byte[]{ 'G', 'o', 'n', 'e' }),
    LENGTH_REQUIRED(411, new byte[]{ 'L', 'e', 'n', 'g', 't', 'h', 0x20, 'R', 'e', 'q', 'u', 'i', 'r', 'e', 'd' }),
    PRECONDITION_FAILED(412, new byte[]{ 'P', 'r', 'e', 'c', 'o', 'n', 'd', 'i', 't', 'i', 'o', 'n', 0x20, 'F', 'a', 'i', 'l', 'e', 'd' }),
    REQUEST_TOO_LONG(413, new byte[]{ 'P', 'a', 'y', 'l', 'o', 'a', 'd', 0x20, 'T', 'o', 'o', 0x20, 'L', 'a', 'r', 'g', 'e' }),
    REQUEST_URI_TOO_LONG(414, new byte[]{ 'U', 'R', 'I', 0x20, 'T', 'o', 'o', 0x20, 'L', 'o', 'n', 'g' }),
    UNSUPPORTED_MEDIA_TYPE(415, new byte[]{ 'U', 'n', 's', 'u', 'p', 'p', 'o', 'r', 't', 'e', 'd', 0x20, 'M', 'e', 'd', 'i', 'a', 0x20, 'T', 'y', 'p', 'e' }),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, new byte[]{ 'R', 'a', 'n', 'g', 'e', 0x20, 'N', 'o', 't', 0x20, 'S', 'a', 't', 't', 'i', 's', 'f', 'i', 'a', 'b', 'l', 'e' }),
    EXPECTATION_FAILED(417, new byte[]{ 'E', 'x', 'p', 'e', 'c', 't', 'a', 't', 'i', 'o', 'n', 0x20, 'F', 'a', 'i', 'l', 'e', 'd' }),
    MISDIRECTED_REQUEST(421, new byte[]{ 'M', 'i', 's', 'd', 'i', 'r', 'e', 'c', 't', 'e', 'd', 0x20, 'R', 'e', 'q', 'u', 'e', 's', 't' }),
    UNPROCESSABLE_ENTITY(422, new byte[]{ 'U', 'n', 'p', 'r', 'o', 'c', 'e', 's', 's', 'a', 'b', 'l', 'e', 0x20, 'E', 'n', 't', 'i', 't', 'y' }),
    LOCKED(423, new byte[]{ 'L', 'o', 'c', 'k', 'e', 'd' }),
    FAILED_DEPENDENCY(424, new byte[]{ 'F', 'a', 'i', 'l', 'e', 'd', 0x20, 'D', 'e', 'p', 'e', 'n', 'd', 'e', 'n', 'c', 'y' }),
    TOO_EARLY(425, new byte[]{ 'T', 'o', 'o', 0x20, 'E', 'a', 'r', 'l', 'y' }),
    UPGRADE_REQUIRED(426, new byte[]{ 'U', 'p', 'g', 'r', 'a', 'd', 'e', 0x20, 'R', 'e', 'q', 'u', 'i', 'r', 'e', 'd' }),
    PRECONDITION_REQUIRED(428, new byte[]{ 'P', 'r', 'e', 'c', 'o', 'n', 'd', 'i', 't', 'i', 'o', 'n', 0x20, 'R', 'e', 'q', 'u', 'i', 'r', 'e', 'd' }),
    TOO_MANY_REQUESTS(429, new byte[]{ 'T', 'o', 'o', 0x20, 'M', 'a', 'n', 'y', 0x20, 'R', 'e', 'q', 'u', 'e', 's', 't', 's' }),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, new byte[]{ 'R', 'e', 'q', 'u', 'e', 's', 't', 0x20, 'H', 'e', 'a', 'd', 'e', 'r', 0x20, 'F', 'i', 'e', 'l', 'd', 's', 0x20, 'T', 'o', 'o', 0x20, 'L', 'a', 'r', 'g', 'e' }),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, new byte[]{ 'U', 'n', 'a', 'v', 'a', 'i', 'l', 'a', 'b', 'l', 'e', 0x20, 'F', 'o', 'r', 0x20, 'L', 'e', 'g', 'a', 'l', 0x20, 'R', 'e', 'a', 's', 'o', 'n', 's' }),

    //5xx: Server Error
    INTERNAL_SERVER_ERROR(500, new byte[]{ 'I', 'n', 't', 'e', 'r', 'n', 'a', 'l', 0x20, 'S', 'e', 'r', 'v', 'e', 'r', 0x20, 'E', 'r', 'r', 'o', 'r' }),
    NOT_IMPLEMENTED(501, new byte[]{ 'N', 'o', 't', 0x20, 'I', 'm', 'p', 'l', 'e', 'm', 'e', 'n', 't', 'e', 'd' }),
    BAD_GATEWAY(502, new byte[]{ 'B', 'a', 'd', 0x20, 'G', 'a', 't', 'e', 'w', 'a', 'y' }),
    SERVICE_UNAVAILABLE(503, new byte[]{ 'S', 'e', 'r', 'v', 'i', 'c', 'e', 0x20, 'U', 'n', 'a', 'v', 'a', 'i', 'l', 'a', 'b', 'l', 'e' }),
    GATEWAY_TIMEOUT(504, new byte[]{ 'G', 'a', 't', 'e', 'w', 'a', 'y', 0x20, 'T', 'i', 'm', 'e', 'o', 'u', 't' }),
    HTTP_VERSION_NOT_SUPPORTED(505, new byte[]{ 'H', 'T', 'T', 'P', 0x20, 'V', 'e', 'r', 's', 'i', 'o', 'n', 0x20, 'N', 'o', 't', 0x20, 'S', 'u', 'p', 'p', 'o', 'r', 't', 'e', 'd' }),
    VARIANT_ALSO_NEGOTIATES(506, new byte[]{ 'V', 'a', 'r', 'i', 'a', 'n', 't', 0x20, 'A', 'l', 's', 'o', 0x20, 'N', 'e', 'g', 'o', 't', 'i', 'a', 't', 'e', 's' }),
    INSUFFICIENT_STORAGE(507, new byte[]{ 'I', 'n', 's', 'u', 'f', 'f', 'i', 'c', 'i', 'e', 'n', 't', 0x20, 'S', 't', 'o', 'r', 'a', 'g', 'e' }),
    LOOP_DETECTED(508, new byte[]{ 'L', 'o', 'o', 'p', 0x20, 'D', 'e', 't', 'e', 'c', 't', 'e', 'd' }),
    NOT_EXTENDED(510, new byte[]{ 'N', 'o', 't', 0x20, 'E', 'x', 't', 'e', 'n', 'd', 'e', 'd' }),
    NETWORK_AUTHENTICATION_REQUIRED(511, new byte[]{ 'N', 'e', 't', 'w', 'o', 'r', 'k', 0x20, 'A', 'u', 't', 'h', 'e', 'n', 't', 'i', 'c', 'a', 't', 'i', 'o', 'n', 0x20, 'R', 'e', 'q', 'u', 'i', 'r', 'e', 'd' });

    private final int value;
    private final byte[] description;

    StatusCode(int value, byte[] description){
        this.value = value;
        this.description = description;
    }

    public int getValue(){
        return value;
    }

    public String getDescription(){
        return new String(description);
    }

    public byte[] getByteDescription(){
        return description;
    }

    @Override
    public String toString(){
        return value+" "+description;
    }

    public static StatusCode getByValue(int value){
        for(StatusCode status : values()){
            if(status.value == value){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + value);
    }
}
