/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test for the CloseFrame class
 */
public class CloseFrameTest {

    @Test
    public void testConstructor() {
        CloseFrame frame = new CloseFrame();
        assertEquals(Opcode.CLOSING, frame.getOpcode(), "Opcode must be equal");
        assertTrue(frame.isFin(), "Fin must be set");
        assertFalse(frame.getTransfereMasked(), "TransferedMask must not be set");
        assertEquals( 2, frame.getPayloadData().capacity(), "Payload must be 2 (close code)");
        assertFalse(frame.isRSV1(), "RSV1 must be false");
        assertFalse(frame.isRSV2(), "RSV2 must be false");
        assertFalse(frame.isRSV3(), "RSV3 must be false");
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
    }

    @Test
    public void testExtends() {
        CloseFrame frame = new CloseFrame();
        assertInstanceOf(ControlFrame.class, frame, "Frame must extend dataframe");
    }

    @Test
    public void testToString() {
        CloseFrame frame = new CloseFrame();
        String frameString = frame.toString();
        frameString = frameString.replaceAll("payload:(.*)}", "payload: *}");
        assertEquals(
                "Framedata{ opcode:CLOSING, fin:true, rsv1:false, rsv2:false, rsv3:false, payload length:[pos:0, len:2], payload: *}code: 1000",
                frameString, "Frame toString must include a close code");
    }


    @Test
    public void testIsValid() {
        CloseFrame frame = new CloseFrame();
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setFin(false);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setFin(true);
        frame.setRSV1(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV1(false);
        frame.setRSV2(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV2(false);
        frame.setRSV3(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV3(false);
        frame.setCode(CloseCodeConstants.NORMAL);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.GOING_AWAY);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.PROTOCOL_ERROR);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.REFUSE);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.NOCODE);
        assertEquals(0, frame.getPayloadData().capacity());
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.ABNORMAL_CLOSE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.POLICY_VALIDATION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.TOOBIG);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.EXTENSION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.UNEXPECTED_CONDITION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.SERVICE_RESTART);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.TRY_AGAIN_LATER);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.BAD_GATEWAY);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseCodeConstants.TLS_ERROR);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.NEVER_CONNECTED);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.BUGGYCLOSE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.FLASHPOLICY);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.NOCODE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.NO_UTF8);
        frame.setReason(null);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseCodeConstants.NOCODE);
        frame.setReason("Close");
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
    }
}
