.include beta.uasm
.include intio.uasm
.options tty

	CMOVE(pile,SP)
	BR(debut)
a:	 LONG(0)
f:
	PUSH(LP)
	PUSH(BP)
	MOVE(SP,BP)
	ALLOCATE(1)
	GETFRAME(-16,R0)
	PUSH(R0)
	GETFRAME(-12,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	ADD(R1,R2,R0)
	PUSH(R0)
	POP(R0)
	PUTFRAME(R0,0)
	GETFRAME(0,R0)
	PUSH(R0)
	CMOVE(10,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	ADD(R1,R2,R0)
	PUSH(R0)
	POP(R0)
	PUTFRAME(R0,-20)
	BR(return_f)
return_f:
	DEALLOCATE(1)
	POP(BP)
	POP(LP)
	RTN()
main:
	PUSH(LP)
	PUSH(BP)
	MOVE(SP,BP)
	ALLOCATE(0)
	ALLOCATE(1)
	CMOVE(1,R0)
	PUSH(R0)
	CMOVE(2,R0)
	PUSH(R0)
	CALL(f)
	DEALLOCATE(2)
	POP(R0)
	ST(R0,a)
	LD(a,R0)
	PUSH(R0)
	POP(R0)
	WRINT()
return_main:
	DEALLOCATE(0)
	POP(BP)
	POP(LP)
	RTN()
debut:
	CALL(main)
	HALT()
pile:
