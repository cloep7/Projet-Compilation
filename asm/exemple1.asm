.include beta.uasm
.include intio.uasm
.options tty

	CMOVE(pile,SP)
	BR(debut)
main:
	PUSH(LP)
	PUSH(BP)
	MOVE(SP,BP)
	ALLOCATE(0)
return_main:
	DEALLOCATE(0)
	POP(BP)
	POP(LP)
	RTN()
debut:
	CALL(main)
	HALT()
pile:

