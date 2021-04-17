import { User } from './entities/user.entity';
import {
  Controller,
  Get,
  Post,
  Body,
  Param,
  Delete,
  Req,
  Put,
} from '@nestjs/common';
import { UserService } from './user.service';

@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  async create(@Body() user: User) {
    try {
      return await this.userService.create(user);
    } catch (error) {
      return await error;
    }
  }

  @Get()
  findAll() {
    return this.userService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: number) {
    try {
      return await this.userService.findOne(+id);
    } catch (error) {
      return await error;
    }
  }

  @Put(':id')
  async update(@Param('id') id: number, @Body() user: User) {
    try {
      return await this.userService.update(id, user);
    } catch (error) {
      return await error;
    }
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    const val: boolean = this.userService.remove(+id);
    try {
      return val;
    } catch (error) {
      return `{success:${val}}`;
    }
  }

  /**
   * for login the user
   * @param req
   */
  @Post('login')
  async getLogin(@Req() req) {
    return await this.userService.login(req.body.email, req.body.password);
  }

  /**
   * get the token refreshed
   * @param req
   */
  @Post('refresh')
  async getRefreshToken(@Req() req) {
    return await this.userService.verifyRefreshToken(req.body.refreshToken);
  }
}
